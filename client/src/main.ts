import { mount } from 'svelte'
import './app.css'
import App from './App.svelte'

export interface Task {
  id: number;
  name: string;
  description: string;
  priority: number;
}

export const priorities = [
  { value: 0, name: 'Low Priority' },
  { value: 1, name: 'Medium Priority' },
  { value: 2, name: 'High Priority' },
]

const app = mount(App, {
  target: document.getElementById('app')!,
})

export default app
