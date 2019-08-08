(ns calendar.core-test
  (:require [calendar.core :as core]
            [clj-time.core :as t]
            [clojure.test :refer [deftest is testing]]))

(def event1 {:name "event 1" :start-time (t/today-at 8 00) :end-time (t/today-at 9 00)})

(def overlapping1 {:name "Overlapping 1" :start-time (t/today-at 10 00) :end-time (t/today-at 11 00)})
(def overlapping2 {:name "Overlapping 2"
                   :description "Starts during ends during"
                   :start-time (t/today-at 10 15) :end-time (t/today-at 10 30)})
             

(deftest overlapping-test
  (testing "do two events overlap"
    (is (true? (core/overlapping? overlapping1 overlapping2))))
  (testing "events that do not overlap"
    (is (false? (core/overlapping? event1 overlapping2)))))  
