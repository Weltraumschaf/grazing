# Grazing

You can build the tool with [Git][git], [Java 8][jdk] and [Maven][mvn]:

```bash
$> git clone https://github.com/Weltraumschaf/grazing.git
$> cd grazing
$> mvn clean install
```

Or you can download the latest binary [here][bin]. For Linux/Unix users you only need the `grazing` binary. Just run it with `grazing -h`. Windows users need the the Jar file and execute it with `java -jar <JAR-FILE> -h`. This will give the output:

```
Usage: grazing -f file/with/isins.txt

This tool grazes information about ETFs for given ISINs.

Options

  -c, --csv           Write result as CSV to given file.
      --debug         Print debug output such as stack traces to STDOUT.
  -f, --file          Give file with ISINs.
  -h, --help          Show help.
  -i, --isin          Scrape the ISIN URL.
  -v, --version       Version.

Example

  Graze information for one particular ISIN:
  $> grazing -i ISIN

  Graze information for multiple ISINs from a text file:
  $> grazing -f file/with/isins.txt

  Graze multiple ISINs from file and write result to CSV file:
  $> grazing -f file/with/isins.txt -c file/to/save.csv
```

[git]:  https://git-scm.com/downloads
[jdk]:  http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[jre]:  http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[mvn]:  https://maven.apache.org/download.cgi?Preferred=ftp%3A%2F%2Fmirror.reverse.net%2Fpub%2Fapache%2F
[bin]:  https://ci.weltraumschaf.de/job/grazing/lastSuccessfulBuild/artifact/target/grazing-1.0.0-SNAPSHOT.zip