<template>
  <section>
    <ul>
      <li v-for="(todoItem, index) in todoItems" :key="todoItem.timestamp" class="shadow">
        <i class="checkBtn fa fa-check" aria-hidden="true"></i>
        {{ todoItem.todo }}
        <span
          class="removeBtn"
          type="button"
          @click="removeTodo(todoItem, index)"
        >
          <i class="fa fa-trash" aria-hidden="true"></i>
        </span>
      </li>
    </ul>
  </section>
</template>

<script>
import eventBus from '../eventBus.js';

export default {
  data() {
    return {
      todoItems: [],
    };
  },
  methods: {
    removeTodo(key, index) {
      localStorage.removeItem(this.todoItems[index].timestamp);
      console.log(this.todoItems[key]);
      this.todoItems.splice(index, 1);
    },
    getDataFromLocalStorage() {
      this.todoItems = [];

      for (const idx in Object.keys(localStorage)) {
        if (localStorage.key(idx) !== "loglevel:webpack-dev-server") {
          this.todoItems.push({
            timestamp: localStorage.key(idx),
            todo: localStorage[localStorage.key(idx)]
          });
        }
      }

      this.todoItems = this.todoItems.sort((a, b) => {
        return b.timestamp - a.timestamp;
      });
    }
  },
  created() {
    this.getDataFromLocalStorage()

    eventBus.$on('added', () => {
      this.getDataFromLocalStorage()
    })

    eventBus.$on('cleared', () => {
      this.getDataFromLocalStorage()
    })
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}

li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
}

.checkBtn {
  line-height: 45px;
  color: #62acde;
  margin-right: 5px;
}

.removeBtn {
  margin-left: auto;
  color: #de4343;
}
</style>