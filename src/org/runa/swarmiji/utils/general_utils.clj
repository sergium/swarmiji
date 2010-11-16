(ns org.runa.swarmiji.utils.general-utils)

(import '(java.util Random UUID))
(use '[clojure.contrib.duck-streams :only (spit)])
(import '(java.lang.management ManagementFactory))
(use 'org.rathore.amit.utils.clojure)
(use 'org.rathore.amit.utils.rabbitmq)

(defn random-uuid []
  (str (UUID/randomUUID)))

(defn return-queue-name [sevak-name]
  (str (System/currentTimeMillis) "_" sevak-name "_" (random-uuid)))

(defn random-queue-name 
  ([]
     (random-queue-name ""))
  ([prefix]
     (str prefix (random-uuid))))

(defn process-pid []
  (let [m-name (.getName (ManagementFactory/getRuntimeMXBean))]
    (first (.split m-name "@"))))

(defn simulate-serialized [hash-object]
  (read-string (str hash-object)))

(defmacro with-prefetch-count [prefetch-count & body]
  `(binding [*PREFETCH-COUNT* ~prefetch-count]
     (do ~@body)))