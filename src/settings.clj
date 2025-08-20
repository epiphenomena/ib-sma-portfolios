(ns settings
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [utils]))

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

