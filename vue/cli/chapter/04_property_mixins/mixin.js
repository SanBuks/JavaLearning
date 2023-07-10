export const commonMethods= {
  methods: {
    showInfo() {
      alert(this.mName)
    },
  },
}

export const commonData = {
  data() {
    return {
      x: 10,
      y: 'abc',
    }
  },
}

export const commonMounted= {
  mounted() {
    console.log(this)
    console.log("已挂载")
  }
}
