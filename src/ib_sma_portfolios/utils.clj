(ns ib-sma-portfolios.utils
  (:require [clojure.math :as math])
  (:import [java.io File]))

(defn get-file-info
  "Get file modification time and size information"
  [file-path]
  (let [file (File. file-path)]
    {:last-modified (.lastModified file)
     :size (.length file)
     :hours-since-modified (-> (.lastModified file)
                               (- (System/currentTimeMillis))
                               (/ 1000) ; seconds
                               (/ 3600) ; hours
                               long
                               Math/abs)}))