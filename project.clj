(defproject blueshift-riemann-metrics "0.1.0-SNAPSHOT"
  :description "Adds a Riemann metrics reporter for using with Blueshift"
  :url "https://github.com/uswitch/blueshift-riemann-metrics"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[com.aphyr/riemann-java-client "0.2.10" :exclusions [org.slf4j/slf4j-api
                                                                      com.codahale.metrics/metrics-core]]]
  :profiles {:provided {:dependencies [[metrics-clojure "2.0.2"]
                                       [com.stuartsierra/component "0.2.1"]
                                       [org.clojure/tools.logging "0.2.6"]
                                       [org.clojure/clojure "1.6.0"]]}
             :dev {:dependencies [[org.slf4j/slf4j-simple "1.7.7"]]}})
