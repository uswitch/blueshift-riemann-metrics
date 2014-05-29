# Riemann Metrics for Blueshift

[Blueshift](https://github.com/uswitch/blueshift) is a service to provide an easier S3-oriented integration with Amazon Redshift. This library provides [Metrics](http://metrics.codahale.com/) integration for Riemann suitable for integrating with Blueshift.

## Usage

Assuming you've already got an Uberjar built for Blueshift you just need to build an uberjar, add it to your classpath and add a reporter to the telemetry section of Blueshift's configuration; [Blueshift's README](https://github.com/uswitch/blueshift/blob/master/README.md) has a bit more info on how to do this.

## Authors

* [Paul Ingles](https://github.com/pingles) ([@pingles](http://twitter.com/pingles)

## License

Copyright © 2014 [uSwitch.com Limited](http://www.uswitch.com).

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
