// #1. Symbol() 创建的对象都是独一无二的值, 即使 description 不一样
let sym1 = Symbol('foo')
let sym2 = Symbol('foo')
console.log(sym1 === sym2)
//     Symbol.for 注册的全局对象是唯一的
let sym3 = Symbol.for('foo')
let sym4 = Symbol.for('foo')
console.log(sym3 === sym4)
//     获取全局 symbol 的键
console.log(Symbol.keyFor(sym3))

console.log('--------------------------------')
// #2. symobol 无法通过 new 创建包装对象
//     可以通过 Object(sym) 形式创建
let numObj = new Number(3)
console.log(typeof numObj)
// let noSymbolObj = new Symbol(); // error
let symObj = Object(Symbol())
console.log(typeof symObj)

console.log('--------------------------------')
// #3. 防止对象属性冲突
const person = {
    [sym1]: "123",
    [sym2]: "321"
}
console.log(person)
console.log(person[sym1])
console.log(person[sym2])

// #4. 枚举类统一调用方式
const DAY = Object.freeze({
    monday: Symbol("monday"),
    tuesday: Symbol("tuesday"),
    wednesday: Symbol("wednesday")
})
