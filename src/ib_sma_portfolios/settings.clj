(ns ib-sma-portfolios.settings
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]))

(defn get-data-dir []
  "Get the data directory from system property or default path"
  (or (System/getProperty "ib.sma.portfolios.data.dir") 
      "/home/unveiled/work/P123/script/ib_data"))

(defn read-json-file
  "Reads a JSON file and returns the parsed data."
  [file-path]
  (try
    (with-open [reader (io/reader file-path)]
      (json/read reader))
    (catch Exception e
      (println (str "Error reading " file-path ": " (.getMessage e)))
      {})))

(def account-settings-file-path
  "Path to the account_settings.json file"
  (str (get-data-dir) "/account_settings.json"))

(def allocator-settings-file-path
  "Path to the allocator_settings.json file"
  (str (get-data-dir) "/allocator_settings.json"))

(def portfolio-settings-file-path
  "Path to the portfolio_settings.json file"
  (str (get-data-dir) "/portfolio_settings.json"))

(def account-settings
  "Account settings loaded from account_settings.json"
  (read-json-file account-settings-file-path))

(def allocator-settings
  "Allocator settings loaded from allocator_settings.json"
  (read-json-file allocator-settings-file-path))

(def portfolio-settings
  "Portfolio settings loaded from portfolio_settings.json"
  (read-json-file portfolio-settings-file-path))

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