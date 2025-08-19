(ns ib-sma-portfolios.core
  (:require [clojure.data.json :as json]
            [clj-http.client :as http]
            [cheshire.core :as cheshire]))

(defn -main []
  (println "Hello, IB SMA Portfolios!")
  (flush))