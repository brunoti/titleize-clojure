(ns excelsia.titleize
  (:require [clojure.string :refer [lower-case upper-case replace]]))

(defn titleize [str]
  (-> str
      (lower-case)
      (replace #"(?:^|\s|-)\S" upper-case)))
