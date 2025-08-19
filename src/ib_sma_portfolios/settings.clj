(ns ib-sma-portfolios.settings
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]))

(defn read-json-file
  "Reads a JSON file and returns the parsed data."
  [file-path]
  (try
    (with-open [reader (io/reader file-path)]
      (json/read reader))
    (catch Exception e
      (println (str "Error reading " file-path ": " (.getMessage e)))
      {})))

(def account-settings
  "Account settings loaded from account_settings.json"
  (read-json-file "/home/unveiled/work/P123/script/ib_data/account_settings.json"))

(def allocator-settings
  "Allocator settings loaded from allocator_settings.json"
  (read-json-file "/home/unveiled/work/P123/script/ib_data/allocator_settings.json"))

(def portfolio-settings
  "Portfolio settings loaded from portfolio_settings.json"
  (read-json-file "/home/unveiled/work/P123/script/ib_data/portfolio_settings.json"))

(comment
  ;; Example usage:
  ;; Get all account settings
  account-settings
  
  ;; Get specific account info
  (get account-settings "U1667447")
  
  ;; Get allocator settings
  allocator-settings
  
  ;; Get portfolio settings
  portfolio-settings
  
  ;; Get specific portfolio info
  (get portfolio-settings "Omega"))