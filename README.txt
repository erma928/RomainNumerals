### INSRUCTIONS TO RUN

1. 解压缩，在galaxy目录下, 运行 `ant`
2. `java -cp galaxy.jar com.galaxy.GalaxyApp <path to input file>`


### PROGRAM DESIGN

1. 主要相关类设计:
    `RomanSymbol`: enum类，支持定义罗马数字符号和数值，以及其相关属性和支持的操作
	`GalaxyRomanNumeral`: 支持罗马数与阿拉伯数字的转化逻辑，采用了一个线性转化算法
	`GalaxyTranslator`: 支持星系符号与罗马数的转化逻辑
	`GalaxyNotesParser`: 支持笔记文本的信息解析和问题解答，主要利用正则表达式
2. 罗马数与阿拉伯数转化算法:
    线性复杂度，在一个循环内，采用stack数据结构，存储中间计算的数值，支持各种罗马数规则检验。
3. 更多信息:
	请看代码及其文档。