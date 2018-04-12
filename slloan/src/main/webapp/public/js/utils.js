//  自定义库

// 简洁版本 log
var log = console.log.bind(console)

// 获取一个 DOM 数组
var es = function(elements) {
    return document.querySelectorAll(elements)
}

//  获取一个 DOM 节点
var e = function(element) {
  return document.querySelector(element)
}
