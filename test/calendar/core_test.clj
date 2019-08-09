(ns calendar.core-test
  (:require [calendar.core :as core]
            [clj-time.core :as t]
            [clojure.test :refer [deftest is testing]]))

(def event1 {:name "event 1" :start-time (t/today-at 8 00) :end-time (t/today-at 9 00)})

(def overlapping1 {:name "Overlapping 1" :start-time (t/today-at 10 00) :end-time (t/today-at 11 00)})
(def overlapping2 {:name "Overlapping 2"
                   :description "Starts during ends during"
                   :start-time (t/today-at 10 15) :end-time (t/today-at 10 30)})

(def overlapping3 {:name "Overlapping 3" :start-time (t/today-at 13 00) :end-time (t/today-at 14 00)})
(def overlapping4 {:name "Overlapping 4" :start-time (t/today-at 12 15) :end-time (t/today-at 16 01)})
             
(def events [event1 overlapping1 overlapping2 overlapping3 overlapping4])

(deftest overlapping?-test
  (testing "do two events overlap"
    (is (true? (core/overlapping? overlapping1 overlapping2))))
  (testing "events that do not overlap"
    (is (false? (core/overlapping? event1 overlapping2))))
  (testing "Same event"
    (is (false? (core/overlapping? event1 event1)))))


(deftest get-overlapping-test
  (testing "Given a list of events and an over lapping event get the other overlapping"
    (is (= [overlapping1 overlapping2]
           (core/get-overlapping events overlapping1))))
  (testing "Given a list of events and an event with no overlapping"
    (is (= []
           (core/get-overlapping events event1)
           ))))

(deftest get-all-overlapping-test
  (testing "Given a list of events return all overlapping sets"
    (is (=  [[overlapping1 overlapping2]
             [overlapping4 overlapping3]]
            (core/all-overlapping events)))))

  

