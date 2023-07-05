Download The Code
Open inside vs code
install required extension
Download Maven binaries from https://maven.apache.org/download.cgi

set up Enviornment variable to use jdk instead of jre

use the following plugin inside pom.xml

```
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.0</version>
        <configuration>
            <release>11</release>
            <executable>C:\Program Files\Java\jdk-16.0.1\bin\javac</executable>
        </configuration>
    </plugin>
```