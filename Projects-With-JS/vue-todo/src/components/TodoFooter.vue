<template>
  <div class="clearAllContainer">
    <span class="clearAllBtn" @click="clearTodo">모두 삭제</span>
  </div>
</template>
  
<script>
import eventBus from '../eventBus.js';

export default {
  methods: {
    clearTodo() {
      localStorage.clear();
      eventBus.$emit('cleared');
    },
  },
};

</script>

<style>
.clearAllContainer {
  width: 8.5rem;
  height: 50px;
  line-height: 50px;
  background-color: white;
  border-radius: 5px;
  margin: 0 auto;
}

.clearAllBtn {
  color: #e20303;
  display: block;
}
</style>