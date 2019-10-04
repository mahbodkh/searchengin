## Command line driven text search engine

#### build it 

```
mvn clean package
```

#### run it 

```
java -jar target/search.engine-1.0-SNAPSHOT.jar ${path_to_txt_files}
```


#### Start it with sample 

```
$ mvn clean package && java -jar target/search.engine-1.0-SNAPSHOT.jar ./src/test/resources/
6 files read in directory ./src/test/resources 

search> to be or not to be
filename1 : 100% 
filename2 : 90% 
search>
search> cats
no matches found 
search> :quit


```


~~~~
Developer:
Ebrahim Khosravani 
ekhosravani@gmail.com