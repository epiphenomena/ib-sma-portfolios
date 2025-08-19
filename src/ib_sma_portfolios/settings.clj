(ns ib_sma_portfolios.settings
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

(defn load-account-settings
  "Load account settings from the specified directory with optional filename.
   Defaults to account_settings.json if no filename is provided."
  ([data-dir]
   (load-account-settings data-dir "account_settings.json"))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)]
     (read-json-file file-path))))

(defn load-allocator-settings
  "Load allocator settings from the specified directory with optional filename.
   Defaults to allocator_settings.json if no filename is provided."
  ([data-dir]
   (load-allocator-settings data-dir "allocator_settings.json"))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)]
     (read-json-file file-path))))

(defn load-portfolio-settings
  "Load portfolio settings from the specified directory with optional filename.
   Defaults to portfolio_settings.json if no filename is provided."
  ([data-dir]
   (load-portfolio-settings data-dir "portfolio_settings.json"))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)]
     (read-json-file file-path))))

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

