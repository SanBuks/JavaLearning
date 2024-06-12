<template>
  <div class="draw">
    <div class="drawTop" ref="drawTop" v-if="lineStep == lineNum">
      <div class="drawTopContrllor" @click="isUnfoldFun">
        <span>{{ isUnfoldText }}</span>
      </div>
      <div>
        <el-button type @click="resetAll">清空</el-button>
        <el-button type @click="repeal">撤销</el-button>
        <el-button type @click="canvasRedo">恢复</el-button>
        <el-button type @click="downLoad">下载</el-button>
        <el-button type @click="resetData">重置</el-button>
      </div>
      <div>
        <el-select v-model="type" placeholder="选择绘制类型">
          <el-option
              v-for="(item, index) in typeOption"
              :key="index"
              :label="item.label"
              :value="item.value"
          ></el-option>
        </el-select>
      </div>
      <div v-if="type == 'P'">
        边数:
        <el-input-number
            v-model="lineNum"
            :min="2"
            :step="1"
            label="描述文字"
            @change="lineStep = lineNum"
        ></el-input-number>
      </div>
      <div v-if="type == 'E'">
        扁的程度:
        <el-input-number
            v-model="ellipseR"
            :min="0.1"
            :max="0.9"
            :step="0.1"
            label="描述文字"
        ></el-input-number>
      </div>
      <div>
        边框粗细:
        <el-input-number
            v-model="lineWidth"
            :min="0"
            :step="0.1"
            label="描述文字"
        ></el-input-number>
      </div>
      <div>
        <el-radio-group v-model="isFill">
          <el-radio :label="true">填充</el-radio>
          <el-radio :label="false">不填充</el-radio>
        </el-radio-group>
      </div>
      <div v-if="isFill">
        填充颜色:
        <el-color-picker v-model="fillStyle"></el-color-picker>
      </div>
      <div>
        线条颜色:
        <el-color-picker v-model="strokeStyle"></el-color-picker>
      </div>
      <div>
        背景颜色:
        <el-color-picker
            v-model="canvasBack"
            @change="changeCanvasBack"
        ></el-color-picker>
      </div>
    </div>
    <div class="drawTop" v-else>
      <div>
        <el-button type @click="resetP('all')">取消</el-button>
      </div>
    </div>
    <section class="content"></section>
  </div>
</template>

<script>
const keys = {
  pc: {
    eventDown: 'onmousedown',
    eventUp: 'onmouseup',
    eventMove: 'onmousemove',
    xKey: 'layerX',
    yKey: 'layerY',
  },
  mobile: {
    eventDown: 'ontouchstart',
    eventUp: 'ontouchend',
    eventMove: 'ontouchmove',
    xKey: 'pageX',
    yKey: 'pageY',
  },
}

export default {
  name: 'draw',
  data() {
    return {
      canvas: '', // 画布对象
      ctx: '', // canvas Context('2d') 对象
      lineWidth: 1, // 线宽
      type: 'L', // 图形类型 默认线条
      typeOption: [
        {label: '线', value: 'L'},
        {label: '多边形', value: 'P'},
        {label: '圆', value: 'C'},
        {label: '矩形', value: 'R'},
        {label: '正方形', value: 'Rs'},
        {label: '椭圆', value: 'E'},
      ],
      isFill: false, // 是否填充
      canvasHistory: [], // 存放画布每一次操作之后的状态
      step: 0, // 历史数组长度记录 截断数组使用
      fillStyle: '#fff', // 填充色
      strokeStyle: '#fff', // 笔触色
      canvasBack: '#000', // 画布背景色
      lineNum: 2, // 多边形边数
      linePeak: [], // 多边形每一步的落点位置
      lineStep: 2, // 多边形剩余步数
      ellipseR: 0.5, // 椭圆两中心点的偏移距离
      isUnfold: false, // 操作区控制选项
      isUnfoldText: '展开操作区',
    }
  },
  computed: {
    // pc 移动 统一参数配置区
    terminal() {
      let terminal = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(
          navigator.userAgent
      )
      let obj = terminal ? keys.mobile : keys.pc
      return Object.assign({}, obj, {isMobile: terminal})
    },
  },
  mounted() {
    let _this = this
    let content = document.getElementsByClassName('content')[0]
    this.canvas = document.createElement('canvas')
    this.canvas.width = content.clientWidth
    this.canvas.height = content.clientHeight

    this.ctx = this.canvas.getContext('2d')

    this.ctx.fillStyle = this.canvasBack
    this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height)

    this.canvasHistory.push(_this.canvas.toDataURL())

    this.ctx.globalCompositeOperation = this.type
    content.appendChild(this.canvas)
    this.bindEventLisner()
  },
  methods: {
    // 手机端是否展开操作区
    isUnfoldFun() {
      this.isUnfold = !this.isUnfold
      if (this.isUnfold) {
        this.$refs.drawTop.style.height = 'auto'
        this.isUnfoldText = '收起操作区'
      } else {
        this.$refs.drawTop.style.height = '30px'
        this.isUnfoldText = '展开操作区'
      }
    },
    // 重置数据
    resetData() {
      this.fillStyle = '#fff'
      this.strokeStyle = '#fff'
      this.canvasBack = '#000'
      this.lineNum = 2
      this.linePeak = []
      this.lineStep = 2
      this.ellipseR = 0.5
      this.lineWidth = 1
      this.type = 'L'
      this.changeCanvasBack()
    },
    // 下载画布
    downLoad() {
      let url = this.canvas.toDataURL('image/png')
      let fileName = 'canvas.png'
      if ('download' in document.createElement('a')) {
        // 非IE下载
        const elink = document.createElement('a')
        elink.download = fileName
        elink.style.display = 'none'
        elink.href = url
        document.body.appendChild(elink)
        elink.click()
        document.body.removeChild(elink)
      } else {
        // IE10+下载
        navigator.msSaveBlob(url, fileName)
      }
    },
    // 底色更改，更改后所有东西都不存在
    changeCanvasBack() {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
      this.ctx.fillStyle = this.canvasBack
      this.ctx.globalAlpha = 1
      this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height)
      this.canvasHistory = []
      this.canvasHistory.push(this.canvas.toDataURL())
      this.step = 0
    },
    // 清空画布及历史记录
    resetAll() {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
      this.ctx.fillStyle = this.canvasBack
      this.ctx.globalAlpha = 1
      this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height)
      this.canvasHistory = []
      this.canvasHistory.push(this.canvas.toDataURL())
      this.step = 0
    },
    // 清空当前画布
    reset(globalAlpha) {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
      this.ctx.fillStyle = this.canvasBack
      this.ctx.globalAlpha = globalAlpha ? globalAlpha : 1
      this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height)
    },
    // 绘制多边形时，取消按钮，及绘制完成之后的数据重置
    resetP(val) {
      this.linePeak = []
      this.lineStep = this.lineNum
      if (val) {
        this.reset()
        this.rebroadcast()
      }
    },
    // 创建线性渐变
    createLinearGradient(x, y, x1, y1) {
      let linearGradient = this.ctx.createLinearGradient(x, y, x1, y1)
      linearGradient.addColorStop(0, 'skyblue')
      linearGradient.addColorStop(1, 'darkturquoise')
      return linearGradient
    },
    // 创建环形渐变
    createRadialGradient(x, y, r, x1, y1, r1) {
      let radialGradient = this.ctx.createRadialGradient(x, y, r, x1, y1, r1)
      radialGradient.addColorStop(0, 'red')
      radialGradient.addColorStop(1, 'white')
      return radialGradient
    },
    // 撤销方法
    repeal() {
      if (this.step >= 1) {
        this.step = this.step - 1
        let canvasPic = new Image()
        console.log(this.step)
        canvasPic.src = this.canvasHistory[this.step]
        canvasPic.addEventListener('load', () => {
          this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
          this.ctx.globalAlpha = 1
          this.ctx.drawImage(canvasPic, 0, 0)
        })
      } else {
        this.$message.warning('不能再继续撤销了')
      }
    },
    // 恢复方法
    canvasRedo() {
      if (this.step < this.canvasHistory.length - 1) {
        if (this.step == 0) {
          this.step = 1
        } else {
          this.step++
        }
        let canvasPic = new Image()
        canvasPic.src = this.canvasHistory[this.step]
        canvasPic.addEventListener('load', () => {
          this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
          this.ctx.globalAlpha = 1
          this.ctx.drawImage(canvasPic, 0, 0)
        })
      } else {
        this.$message.warning('已经是最新的记录了')
      }
    },
    // 绘制历史数组中的最后一个
    rebroadcast() {
      let _this = this
      return new Promise((resolve, reject) => {
        let canvasPic = new Image()
        canvasPic.src = _this.canvasHistory[_this.step]
        canvasPic.addEventListener('load', () => {
          _this.ctx.clearRect(0, 0, _this.canvas.width, _this.canvas.height)
          _this.ctx.globalAlpha = 1
          _this.ctx.drawImage(canvasPic, 0, 0)
          resolve()
        })
      })
    },
    // 获取角度,绘制椭圆
    getAngle(px, py, mx, my) {
      let x = Math.abs(px - mx)
      let y = Math.abs(py - my)
      let z = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
      let cos = x / z
      let radina = Math.acos(cos) //用反三角函数求弧度
      let angle = Math.floor(180 / (Math.PI / radina)) //将弧度转换成角度
      if (mx > px && my == py) {
        //鼠标在x轴正方向上
        angle = 0
      }
      if (mx > px && my < py) {
        //鼠标在第一象限
        angle = -angle
      }
      if (mx == px && my > py) {
        //鼠标在y轴负方向上
        angle = 90
      }
      if (mx < px && my < py) {
        //鼠标在第二象限
        angle = -90 - (90 - angle)
      }
      if (mx < px && my == py) {
        //鼠标在x轴负方向
        angle = -180
      }
      if (mx < px && my > py) {
        //鼠标在第三象限
        angle = -180 - angle
      }
      if (mx > px && my > py) {
        //鼠标在第四象限
        angle = angle
      }
      return (angle * Math.PI) / 180
    },
    // 绑定事件,判断分支
    bindEventLisner() {
      const {eventDown, eventUp, isMobile, xKey, yKey} = this.terminal
      let _this = this
      let r1, r2 // 绘制圆形，矩形需要
      this.canvas[eventDown] = function (target) {
        const e = isMobile ? target.changedTouches[0] : target
        r1 = e[xKey]
        r2 = e[yKey]
        _this.useChooseFun(e, 'begin', r1, r2)
      }
      this.canvas[eventUp] = function (target) {
        const e = isMobile ? target.changedTouches[0] : target
        _this.useChooseFun(e, 'end', r1, r2)
      }
    },
    useChooseFun(target, status, r1, r2) {
      let _this = this
      const {eventMove, xKey, yKey, isMobile} = this.terminal
      let fun = this[`draw${this.type}`]
      if (status == 'begin') {
        if (this.type == 'L') {
          this.ctx.beginPath()
          this.ctx.moveTo(target[xKey], target[yKey])
        }
        if (this.type == 'P') {
          return
        }
        _this.canvas[eventMove] = function (target) {
          let e = isMobile ? target.changedTouches[0] : target
          _this.type != 'L' && _this.reset()
          fun?.(e, r1, r2, status)
          _this.type != 'L' && _this.closeStoke(0.5)
        }
      } else if (status == 'end') {
        if (this.type == 'L') {
          this.ctx.closePath()
          _this.setStepAndHistory()
          return
        }
        if (this.type == 'P') {
          fun?.(target, r1, r2, status)
          return
        }
        _this.rebroadcast().then((res) => {
          fun?.(target, r1, r2, status)
          _this.closeStoke(1)
          _this.setStepAndHistory()
        })
      }
    },
    closeStoke(globalAlpha) {
      this.ctx.globalAlpha = globalAlpha
      this.ctx.strokeStyle = this.strokeStyle
      if (this.isFill) this.ctx.fillStyle = this.fillStyle
      this.ctx.lineWidth = this.lineWidth
      this.ctx.closePath()
      this.isFill ? this.ctx.fill() : this.ctx.stroke()
    },
    setStepAndHistory() {
      const {eventMove} = this.terminal
      this.step = this.step + 1
      if (this.step < this.canvasHistory.length - 1) {
        this.canvasHistory.length = this.step // 截断数组
      }
      this.canvasHistory.push(this.canvas.toDataURL())
      this.canvas[eventMove] = null
    },
    drawC(e, r1, r2, status) {
      const {isMobile, xKey, yKey} = this.terminal
      let x = e[xKey],
          y = e[yKey]
      let r
      r = Math.sqrt(Math.pow(r1 - x, 2) + Math.pow(r2 - y, 2))
      this.ctx.beginPath()
      this.ctx.arc(r1, r2, r, 0, 2 * Math.PI)
    },
    drawL(e, r1, r2, status) {
      const {isMobile, xKey, yKey} = this.terminal
      let x = e[xKey],
          y = e[yKey]
      this.ctx.lineTo(x, y)
      this.ctx.strokeStyle = this.strokeStyle
      this.ctx.lineWidth = this.lineWidth
      this.ctx.stroke()
    },
    drawRs(e, r1, r2, status) {
      const {isMobile, xKey, yKey} = this.terminal
      let x = e[xKey],
          y = e[yKey]
      let r
      r = Math.abs(Math.min(x - r1, y - r2))
      let rx = x - r1 < 0 ? -r : r
      let ry = y - r2 < 0 ? -r : r
      this.ctx.beginPath()
      this.ctx.rect(r1, r2, rx, ry)
    },
    drawR(e, r1, r2, status) {
      const {isMobile, xKey, yKey} = this.terminal
      let x = e[xKey],
          y = e[yKey]
      let rx = x - r1
      let ry = y - r2
      this.ctx.beginPath()
      this.ctx.rect(r1, r2, rx, ry)
    },
    // 绘制多边形
    drawP(e, r1, r2, status) {
      let _this = this
      const {isMobile, xKey, yKey} = this.terminal
      let x = e[xKey],
          y = e[yKey]
      if (status == 'begin') {
      } else if (status == 'end') {
        _this.reset()
        _this.linePeak.push({x: x, y: y})
        _this.lineStep = _this.lineStep - 1
        _this.ctx.beginPath()
        _this.ctx.strokeStyle = _this.strokeStyle
        _this.ctx.lineWidth = _this.lineWidth
        for (let i in _this.linePeak) {
          let peak = _this.linePeak[i]
          if (i == 0) {
            _this.ctx.moveTo(peak.x, peak.y)
          } else {
            _this.ctx.lineTo(peak.x, peak.y)
          }
          _this.ctx.stroke()
        }
        if (_this.lineStep == 0) {
          _this.reset()
          _this.rebroadcast().then((res) => {
            _this.ctx.beginPath()
            _this.ctx.strokeStyle = _this.strokeStyle
            _this.ctx.lineWidth = _this.lineWidth
            for (let i in _this.linePeak) {
              let peak = _this.linePeak[i]
              if (i == 0) {
                _this.ctx.moveTo(peak.x, peak.y)
              } else {
                _this.ctx.lineTo(peak.x, peak.y)
              }
            }
            _this.closeStoke(1)
            _this.resetP()
            _this.setStepAndHistory()
          })
        }
      }
    },
    drawE(e, r1, r2, status) {
      const {isMobile, xKey, yKey} = this.terminal
      let x = e[xKey],
          y = e[yKey]
      let r = Math.sqrt(Math.pow(r1 - x, 2) + Math.pow(r2 - y, 2)),
          deg = this.getAngle(r1, r2, x, y)
      this.ctx.beginPath()
      this.ctx.ellipse(r1, r2, r, r * this.ellipseR, deg, 0, 2 * Math.PI)
    },
  },
}
</script>

<style scope>
* {
  box-sizing: border-box;
}

body,
html,
#app {
  overflow: hidden;
}

.draw {
  height: 100%;
  width: 100%;
  min-width: 420px;
  display: flex;
  flex-direction: column;
}

.content {
  flex-grow: 1;
  height: 100%;
  width: 100%;
}

.drawTop {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 5px;
  height: 52px;
}

.drawTop > div {
  display: flex;
  align-items: center;
  padding: 5px 5px;
}

div.drawTopContrllor {
  display: none;
}

@media screen and (max-width: 1200px) {
  .drawTop {
    position: absolute;
    background-color: white;
    width: 100%;
    flex-direction: column;
    align-items: flex-start;
    height: 30px;
    overflow: hidden;
  }

  .drawTopContrllor {
    display: flex !important;
    height: 30px;
    width: 100%;
    justify-content: center;
    align-items: center;
    padding: 0 !important;
  }
}
</style>
