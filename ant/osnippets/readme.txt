1.jar功能
  osnippets.jar
    给Oracle sql developer中UserSnippets.xml文件排序

2.目录
  data：存放需要排序的UserSnippets.xml文件

3.使用方法（如果报OutOfMemoryError: Java heap space，在加后加上内存设置，java -Xms512m -Xmx1024m -jar osnippets.jar）
  java -jar osnippets.jar newSnippetsFile
