let a = 6;
let b = 9;

// --------------------------------------------------
// #1. 标签函数参数类型
// strArray 数组, 包含所有模板字符串, 默认前后会有空字符串(如果是模板字符串怎会替代)
// expressions 数组, 包含所有插值
function simpleTag(strArray, ...expressions) {
    console.log(`strArray type is ${Array.isArray(strArray) ? 'array' : 'object'}`)
    console.log(`strArray value is ${strArray.toString()}`);
    //  '', ' + ', ' = ', ''
    console.log(strArray)

    console.log(`expressions type is ${Array.isArray(expressions) ? 'array' : 'object'}`)
    console.log(`expressions value is ${expressions.toString()}`)

    for (const expr of expressions)
        console.log(expr);
    return 'foobar';
}
let str = simpleTag`${a} + ${b} = ${a+b}`
console.log(str)

// #2. 标签函数参数使用
console.log('--------------------------------------------')
function simpleTagExpand(strArray, ...expressions) {
    console.log(strArray.length)
    console.log(expressions.length)

    let arr = expressions.map((e, i) => {
        return `${e}${strArray[i + 1]}`
    })
    console.log(arr.join(''))
    return 'foobar'
}
str = simpleTagExpand`${a} + ${b} = ${a+b}, ${a * b}`
console.log(str)

console.log('--------------------------------------------')
// #3. String.raw 直接打印转义字符
console.log(String.raw`123\n${str}`)

function printRaw(templateStrs) {
    console.log('Actual characters:');
    for (const str of templateStrs) {
        console.log(str);
    }

    console.log('Escaped characters;');
// #4. strs.raw 返回会直接打印的字符数组
    for (const rawStr of templateStrs.raw) {
        console.log(rawStr);
    }
}
printRaw`\u00A9${'and'}\n`
