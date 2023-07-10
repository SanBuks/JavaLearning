export const commonMethods= {
  methods: {
    showInfo() {
      alert(this.mName)
    },
  },
}

export const commonMounted= {
  mounted() {
    console.log(this)
    console.log("已挂载")
  }
}
