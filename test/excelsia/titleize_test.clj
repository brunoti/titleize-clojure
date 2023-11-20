(ns excelsia.titleize-test
  (:require [clojure.test :refer [deftest is testing]]
            [excelsia.titleize :as titleize]))

(def result-map {"" ""
                 "unicorns and rainbows" "Unicorns And Rainbows"
                 "UNICORNS AND RAINBOWS" "Unicorns And Rainbows"
                 "unicorns-and-rainbows" "Unicorns-And-Rainbows"
                 "UNICORNS-AND-RAINBOWS" "Unicorns-And-Rainbows"
                 "unicorns   and rainbows" "Unicorns   And Rainbows"})

(deftest titleize-test
  (testing "titleize"
    (doseq [[input expected] result-map]
      (is (= expected (titleize/titleize input))))))
