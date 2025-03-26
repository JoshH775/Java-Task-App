<script lang="ts">
  import CircleCheck from "./lib/CircleCheck.svelte";
  import { Button, Input, Label, Select, Textarea } from 'flowbite-svelte';

  let name = $state('');
  let description = $state('');
  let priority = $state();


  const priorities = [
    { value: 1, name: 'Low Priority' },
    { value: 2, name: 'Medium Priority' },
    { value: 3, name: 'High Priority' },
  ]

  const createTask = () => {
    console.log(name, description, priority);
  }


</script>

<main class="flex flex-col flex-grow w-4/5">

  <header class='h-48 flex flex-col justify-center items-center text-center gap-4 my-8'>
    
    <CircleCheck className='h-18 w-18' />
    <p class='text-4xl font-bold text-nowrap'>Task Manager</p>
    <p class='text-lg'>A simple task manager to keep track of your tasks</p>
  </header>

  <div id="content" class="flex w-full border border-gray-300 rounded-lg p-4 shadow-md gap-2">

    <div id="create-task" class='w-1/2 p-4 gap-4 flex flex-col'>
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

      <Button class='p-3 bg-indigo-600 text-white font-semibold cursor-pointer hover:bg-indigo-700 transition-colors duration-150 w-fit disabled:cursor-not-allowed ' on:click={createTask} disabled={name.length === 0 || !priority}>Create Task</Button>
    </div>

    <div id="task-list" class='w-1/2 p-4'>

    </div>
  </div>

</main>