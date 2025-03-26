<script lang="ts">
  import { onMount } from "svelte";
  import CircleCheck from "./lib/CircleCheck.svelte";
  import { Button, Input, Label, Select, Textarea } from 'flowbite-svelte';
  import { priorities, type Task as TaskType } from "./main";
  import Task from "./lib/Task.svelte";



  let name = '';
  let description = '';
  let priority: number | undefined = undefined;

  let isLoading = false;
  let tasks: TaskType[] = [];



  onMount(async () => {
    isLoading = true
    const response = await fetch('http://localhost:8080/tasks')
    if (response.ok) {
      tasks = await response.json()
      tasks = tasks.sort((a, b) => b.priority - a.priority)
    }
    isLoading = false
  })

  const createTask = async () => {
    const task = { name, description, priority }
    const response = await fetch('http://localhost:8080/tasks', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })

    if (response.ok) {
      const newTask = await response.json()
      tasks.push(newTask)
      tasks = tasks.sort((a, b) => b.priority - a.priority)


      name = ''
      description = ''
      priority = undefined
    }
  }

  function deleteTask(id: number) {
    console.log(id)
        fetch(`http://localhost:8080/tasks/${id}`, {
            method: 'DELETE'
        })
        tasks = tasks.filter(task => task.id !== id)
    }


</script>

<main class="flex flex-col flex-grow w-4/5 ">

  <header class='h-48 flex flex-col justify-center items-center text-center gap-4 my-8'>
    
    <CircleCheck className='h-18 w-18' />
    <p class='text-4xl font-bold text-nowrap'>Task Manager</p>
    <p class='text-lg'>A simple task manager to keep track of your tasks</p>
  </header>

  <div id="content" class="flex w-full border border-gray-300 rounded-lg p-4 shadow-md gap-2  bg-white">

    <div id="create-task" class='w-1/2 p-4 gap-4 h-fit flex flex-col'>
      <p class="text-xl font-bold mb-2">Create Task</p>

      <Label class='flex flex-col space-y-2 '>
        <span class="text-sm font-semibold">Task Name</span>
        <Input type="text" placeholder="What needs to be done?" class='p-3' bind:value={name}/>
      </Label>


      <Label class='flex flex-col space-y-2 '>
        <span class="text-sm font-semibold">Description</span>
        <textarea id="description" class='resize-none border border-gray-300 rounded-md p-3 w-full' placeholder="Add details about this task..." bind:value={description}></textarea>
      </Label>
      
      <Label class='flex flex-col space-y-2 '>
        <span class="text-sm font-semibold">Priority</span>
        <Select items={priorities} bind:value={priority} class='p-3'/>
      </Label>

      <Button class='p-3 bg-indigo-600 text-white font-semibold cursor-pointer hover:bg-indigo-700 transition-colors duration-150 w-fit disabled:cursor-not-allowed ' on:click={createTask} disabled={name.length === 0 || priority === undefined}>Create Task</Button>
    </div>

    <div id="task-list" class='w-1/2 p-4 max-h-128 overflow-y-scroll'>
      {#if isLoading}
        <p>Loading...</p>
      {:else}
        {#if tasks.length === 0}
          <p>No tasks found</p>
        {:else}
          <ul class="space-y-4 ">
            {#each tasks as task}
              <Task {task} onDelete={deleteTask}/>
            {/each}
          </ul>
        {/if}
      {/if}
    </div>
  </div>

</main>