(ns build
  (:require [clojure.tools.build.api :as b]
            [deps-deploy.deps-deploy :as dd]
            [tools-pom.tasks :as pom]))

(def build-folder "target")
(def jar-content (str build-folder "/classes"))     

(def lib-name 'com.github.excelsia-dev/titleize)      
(def version "1.0.0")                               
(def basis (b/create-basis {:project "deps.edn"}))  
(def jar-file-name (format "%s/%s-%s.jar" build-folder (name lib-name) version))  

(defn clean [_]
  (b/delete {:path build-folder})
  (println (format "Build folder \"%s\" removed" build-folder)))

(defn pom [_]
  (pom/pom {
            :lib          lib-name
         :version      version
         :write-pom    true
         :validate-pom true
         :pom          {:description      "Clojure version of sindresorhus/titleize"
                        :url              "https://github.com/excelsia-dev/titleize"
                        :licenses         [:license   {:name "MIT" :url "https://opensource.org/licenses/MIT"}]
                        :developers       [:developer {:id "brunoti" :name "Bruno" :email "brunooliveira37@hotmail.com"}]
                        :scm              {:url                  "https://github.com/excelsia-dev/titleize"
                                           :connection           "scm:git:git://github.com/excelsia-dev/titleize.git"
                                           :developer-connection "scm:git:ssh://git@github.com/excelsia-dev/titleize.git"}
                        :issue-management {:system "github" :url "https://github.com/excelsia-dev/titleize/issues"}}}))

(defn jar [_]
  (clean nil)                                     ; clean leftovers

  (b/copy-dir {:src-dirs   ["source"]    ; prepare jar content
               :target-dir jar-content})

  (b/jar {:class-dir jar-content                  ; create jar
          :jar-file  jar-file-name})

  (pom nil)
  (println (format "Jar file created: \"%s\"" jar-file-name)))

(defn deploy [_]
  (dd/deploy {:installer :remote
              :artifact jar-file-name }))


              
