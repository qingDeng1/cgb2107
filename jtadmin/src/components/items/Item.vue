<template>
  <div>
    <!-- 定义面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 定义卡片 -->
    <el-card class="box-card">

      <!-- 定义第一行数据 -->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryItemInfo.query" clearable @clear="getItemList">
            <el-button slot="append" icon="el-icon-search" @click="getItemList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="toAddItem">添加商品</el-button>
        </el-col>
      </el-row>

      <!-- 获取商品列表数据 -->
      <el-table :data="itemList" style="width: 100%" stripe border>
        <el-table-column type="index" label="序号" width="50px">
        </el-table-column>
        <el-table-column prop="title" label="商品标题" width="400px">
        </el-table-column>
        <el-table-column prop="sellPoint" label="卖点信息" width="300px">
        </el-table-column>
        <!-- 使用过滤器修改时间 在main.js中定义过滤器 -->
        <el-table-column prop="price" label="价格(元)" width="80px">
          <template slot-scope="scope">
            {{scope.row.price | priceFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="num" label="数量" width="80px">
        </el-table-column>
        <el-table-column prop="status" label="状态" width="80px">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949"
              @change="updateStatus(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="updated" label="更新时间" width="180px" :formatter="formatDate">
        </el-table-column> -->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="updateItemBtn(scope.row)">修改</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteItemBtn(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>


      <!-- 定义分页插件-->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="queryItemInfo.pageNum" :page-sizes="[1, 5, 10, 20]" :page-size="queryItemInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>

    </el-card>


    <!-- 添加修改分类对话框 -->
    <!-- 标题/卖点/价格/数量 -->
    <el-dialog title="修改列表商品信息" :visible.sync="updateItemCatDialogVisible" width="50%">
      <!-- 定义分类表单 -->
      <el-form :model="updateItemCatForm" ref="upDateItemCatForm" label-width="100px">
        <el-form-item label="标题:" prop="title">
          <el-input v-model="updateItemCatForm.title"></el-input>
        </el-form-item>
        <el-form-item label="卖点:" prop="sellPoint">
          <el-input v-model="updateItemCatForm.sellPoint"></el-input>
        </el-form-item>
        <el-form-item label="价格:" prop="price">
          <el-input v-model="updateItemCatForm.price"></el-input>
        </el-form-item>
        <el-form-item label="数量:" prop="num">
          <el-input v-model="updateItemCatForm.num"></el-input>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="updateItemCatDialog()" >取 消</el-button>
        <el-button type="primary" @click="updateItem">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
  export default {
    data() {
      return {
        //1.定义商品列表信息
        itemList: [],
        //2.定义分页对象
        queryItemInfo: {
          query: '', //定义查询参数
          pageNum: 1,
          pageSize: 10
        },
        total: 0,
        //定义修改对话框属性
        updateItemCatDialogVisible: false,
        updateItemCatForm: {}

      }
    },
    created() {
      //1.获取商品列表数据
      this.getItemList()
    },
    methods: {
      //由于有层级关系,所有修改只能修改名称
      updateItemBtn(item) {
        this.updateItemCatForm = item
        this.updateItemCatDialogVisible = true
      },

      updateItemCatDialog(){
        this.updateItemCatDialogVisible = false
        this.getItemList()
      },

      async updateItem() {
        //修改商品分类信息
        // 标题/卖点/价格/数量
        const {
          data: result
        } = await this.$http.put(`/item/updateItem`, this.updateItemCatForm)
        if (result.status !== 200) return this.$message.error("修改商品分类信息失败")
        this.$message.success("修改商品分类信息成功")
        this.getItemList();
        this.updateItemCatDialogVisible = false;
      },


      //实现商品信息分页查询
      async getItemList() {
        const {
          data: result
        } = await this.$http.get("/item/getItemList", {
          params: this.queryItemInfo
        })
        if (result.status !== 200) return this.$message.error("商品列表查询失败")
        this.itemList = result.data.rows
        this.total = result.data.total
      },
      //通过JS格式化时间
      formatDate(row, column, cellValue, index) {
        let date = new Date(cellValue)
        let year = date.getFullYear()
        let month = (date.getMonth() + 1 + '').padStart(2, '0')
        let day = (date.getDate() + '').padStart(2, '0')
        let HH = (date.getHours() + '').padStart(2, '0')
        let MM = (date.getMinutes() + '').padStart(2, '0')
        let SS = (date.getSeconds() + '').padStart(2, '0')
        return year + '-' + month + '-' + day + ' ' + HH + ":" + MM + ":" + SS
      },
      //条数变化时 调用
      handleSizeChange(size) {
        this.queryItemInfo.pageSize = size
        this.getItemList()
      },
      //页码值变化时 调用
      handleCurrentChange(current) {
        this.queryItemInfo.pageNum = current
        this.getItemList()
      },
      async updateStatus(item) {
        const {
          data: result
        } = await this.$http.put("/item/updateItemStatus", {
          id: item.id,
          status: item.status
        })
        if (result.status !== 200) return this.$message.error("更新状态失败")
        this.$message.success("更新状态成功")
      },
      async deleteItemBtn(item) {
        //消息确认框
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          //根据id删除数据
          const {
            data: result
          } = await this.$http.delete("/item/deleteItemById", {
            params: {
              id: item.id
            }
          })
          if (result.status !== 200) return this.$message.error("商品删除失败")
          this.$message.success("商品删除成功")
          //重新获取商品列表信息
          this.getItemList()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      //转向到商品新增页面
      toAddItem() {
        this.$router.push("/item/addItem")
      },
      // updateItemBtn() {
      //   console.log("扩展案例,自己实现 只需要修改 标题/卖点/价格/数量")
      // }
    }

  }
</script>
<!-- 防止组件样式冲突 -->
<style lang="less" scoped>

</style>
