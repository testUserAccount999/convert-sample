# convert-sample
sample

struts定義からspringのFormValidatorを生成することを目指します。


## dtd
[struts-1.1](https://archive.apache.org/dist/struts/struts-1.1/jakarta-struts-1.1-src.zip)

## dtd -> xsd <br />

[trang.jar](https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/jing-trang/trang-20091111.zip)

`java -jar trang.far validation_1_1.dtd validation_1_1.xsd`

## xsd -> java <br />
`xjc -p org.sample.struts.validation -encoding UTF-8 src/test/xsd/validation_1_1.xsd`
### memo
- xjcコマンドでエラーが発生したのでvalidation_1_1.xsdを修正
- <arg0\>〜<arg3\>は複数設定できるようなのでさらにvalidation_1_1.xsdを修正
