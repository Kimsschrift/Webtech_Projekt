<!-- src/components/JobList.vue -->
<template>
  <div>
    <h2>Verfügbare Jobs</h2>
    <ul>
      <li v-for="job in jobs" :key="job.id">
        {{ job.title }} in {{ job.location }}
      </li>
    </ul>
  </div>
</template>

<script setup>
const jobs = [
  { id: 1, title: 'Java Backend Developer', location: 'Berlin' },
  { id: 2, title: 'Vue Frontend Engineer', location: 'Hamburg' },
];
</script>
