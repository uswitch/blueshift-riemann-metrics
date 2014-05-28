(ns uswitch.blueshift.telemetry.riemann
  (:require [com.stuartsierra.component :refer (Lifecycle system-map start stop)]
            [clojure.tools.logging :refer (info debug error)])
  (:import [com.codahale.metrics.riemann RiemannReporter Riemann]
           [com.aphyr.riemann.client RiemannClient]
           [java.util.concurrent TimeUnit]
           [java.net InetSocketAddress]))

(defn- riemann-client [host port]
  (info "Creating RiemannClient, connecting to" host port)
  (RiemannClient/tcp (InetSocketAddress. host port)))

(defn- reporter [riemann-client registry]
  (.build (RiemannReporter/forRegistry registry)
          (Riemann. riemann-client)))

(defrecord RiemannMetricsReporter [riemann-config registry]
  Lifecycle
  (start [this]
    (info "Starting RiemannMetricsReporter")
    (let [{:keys [host port]} riemann-config
          c                   (riemann-client host port)
          r                   (reporter c registry)]
      (try (debug "Starting RiemannReporter, reporting every 1s")
           (.start r 1 TimeUnit/SECONDS)
           (catch Exception e
             (error e "Error starting RiemannMetricsReporter")
             this)
           (finally (assoc this :reporter r)))))
  (stop [this]
    (info "Stopping RiemannMetricsReporter")
    (when-let [reporter (:reporter this)]
      (info "Stopping Riemann reporter")
      (.stop reporter))
    (dissoc this :reporter)))

(defn riemann-metrics-reporter [config registry]
  (map->RiemannMetricsReporter {:registry       registry
                                :riemann-config (or (-> config :riemann)
                                                    {:host "127.0.0.1"
                                                     :port 5555})}))
