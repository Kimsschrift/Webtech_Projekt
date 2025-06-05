<template>
  <aside class="filter-sidebar">
    <h3>🔍 Filter</h3>

    <label class="form-group checkbox-inline">
      <span>Home-Office</span>
      <input type="checkbox" v-model="filters.remoteAllowed" />
    </label>


    <div class="form-group">
      <label>Gehalt ab (€):</label>
      <input type="number" v-model.number="filters.minSalary" placeholder="z. B. 40000" />
    </div>

    <div class="form-group">
      <label>Sprachen:</label>

      <div class="language-all">
        <label class="language-option">
          <span>Alle</span>
          <input
              type="checkbox"
              :checked="filters.language.length === 0"
              @change="toggleAllLanguages"
          />
        </label>
      </div>


      <div class="language-list">
        <label
            v-for="lang in allLanguages"
            :key="lang"
            class="language-option"
        >
          <span>{{ lang }}</span>
          <input
              type="checkbox"
              :value="lang"
              v-model="filters.language"
          />
        </label>
      </div>
    </div>



    <div class="form-group">
      <label>Berufsfeld:</label>
      <select v-model="filters.industry">
        <option value="">Alle</option>
        <option value="IT">IT</option>
        <option value="LOGISTICS">Logistics</option>
        <option value="RETAIL">Retail</option>
        <option value="MEDIA">Media</option>
        <option value="AUTOMOTIVE">Automotive</option>
        <option value="FINANCE">Finance</option>
        <option value="MEDICAL">Medical</option>
        <option value="ENERGY">Energy</option>
        <option value="AEROSPACE">Aerospace</option>
        <option value="OTHER">Other</option>
      </select>
    </div>

    <div class="form-group">
      <label>Arbeitszeit:</label>
      <select v-model="filters.workTime">
        <option value="">Alle</option>
        <option value="FULL_TIME">Full Time</option>
        <option value="PART_TIME">Part Time</option>
        <option value="MINI_JOB">MiniJob</option>
        <option value="OTHER">Other</option>
      </select>
    </div>

    <div class="form-group">
      <label>Anstellungsart:</label>
      <select v-model="filters.employmentType">
        <option value="">Alle</option>
        <option value="INTERN">Intern</option>
        <option value="APPRENTICESHIP">Apprenticeship</option>
        <option value="WORK_STUDENT">Work Student</option>
        <option value="FULL_TIME">Full Time</option>
        <option value="OTHER">Other</option>
      </select>
    </div>


    <label class="form-group checkbox-inline">
      <span> Berufserfahrung erforderlich</span>
      <input type="checkbox" v-model="filters.requiresExperience" />
    </label>
  </aside>
</template>


<script>
export default {
  name: 'JobFilterSidebar',
  data() {
    return {
      filters: {
        remoteAllowed: false,
        minSalary: 0,
        language: [],
        industry: '',
        workTime: '',
        employmentType: '',
        requiresExperience: false
      },
      allLanguages: [
        "German", "English", "Spanish", "Italian", "Turkish",
        "French", "Russian", "Arabic", "Persian", "Korean",
        "Japanese", "Chinese"
      ]
    };
  },
  watch: {
    filters: {
      handler(newVal) {
        this.$emit('apply-filters', newVal);
      },
      deep: true
    }
  },
  methods: {
    toggleAllLanguages() {

      this.filters.language = [];

      this.$emit('apply-filters', this.filters)
    }
  }
};
</script>

<style scoped>
.filter-sidebar {
  border-left: 6px solid #1a73e8;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05);
  padding: 2rem;
  width: 280px;
}
.form-group {
  margin-bottom: 1rem;
  font-size: 1rem;
}
.form-group label {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.checkbox-inline {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 0.5rem;
  white-space: nowrap;
}

.checkbox-inline label {
  white-space: nowrap;
  font-size: 0.95rem;
}

input, select {
  width: 100%;
  padding: 0.6rem 0.8rem;
  margin-top: 0.3rem;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 0.95rem;
  transition: border-color 0.3s;
}

input:focus, select:focus {
  border-color: #1a73e8;
  outline: none;
  box-shadow: 0 0 4px rgba(26, 115, 232, 0.3);
}

.language-checkboxes {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.language-option {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 0.95rem;
  padding: 0.3rem 0;
  border-bottom: 1px solid transparent;
}

.language-list {
  max-height: 130px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 0.5rem;
  border-radius: 5px;
  margin-top: 0.3rem;
  background-color: white;
}





</style>
