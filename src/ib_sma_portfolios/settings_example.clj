(ns ib-sma-portfolios.settings-example
  (:require [ib-sma-portfolios.settings :as settings]))

;; Example usage of the settings data

;; Get all account settings
(println "Account settings keys:" (keys settings/account-settings))

;; Get specific account info
(let [account-id "U1667447"
      account-info (get settings/account-settings account-id)]
  (println (str "Account " account-id " info:") account-info)
  (println (str "Account " account-id " description:") (:desc account-info)))

;; Get allocator settings
(println "Allocator settings:" settings/allocator-settings)
(println "Default account:" (:default_acct settings/allocator-settings))
(println "IB port number:" (:ib_port_num settings/allocator-settings))

;; Get portfolio settings
(println "Portfolio settings keys:" (keys settings/portfolio-settings))

;; Get specific portfolio info
(let [portfolio-name "Omega"
      portfolio-info (get settings/portfolio-settings portfolio-name)]
  (println (str "Portfolio " portfolio-name " info:") portfolio-info)
  (println (str "Portfolio " portfolio-name " num_recs:") (:num_recs portfolio-info)))

;; Find accounts with special flag set to true
(println "Accounts with special flag:")
(->> settings/account-settings
     (filter (fn [[_ v]] (:special v)))
     (map (fn [[k v]] [k (:desc v)]))
     (run! println))

;; Find portfolios with num_recs > 50
(println "Portfolios with num_recs > 50:")
(->> settings/portfolio-settings
     (filter (fn [[_ v]] (> (:num_recs v) 50)))
     (map (fn [[k v]] [k (:num_recs v)]))
     (run! println))