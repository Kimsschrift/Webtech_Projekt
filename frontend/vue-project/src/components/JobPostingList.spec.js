import { mount, flushPromises } from '@vue/test-utils'
import JobPostingList from './JobPostingList.vue'
import axios from 'axios'

vi.mock('axios')

describe('JobPostingList', () => {
  it('renders heading', async () => {
    axios.get.mockResolvedValue({ data: [] })
    const wrapper = mount(JobPostingList)
    await flushPromises()
    expect(wrapper.text()).toContain('Offene Stellenanzeigen')
  })
})
