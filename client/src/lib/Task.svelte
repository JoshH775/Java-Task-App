<script lang="ts">
  import type { Task } from "../main";

    export let task: Task
    export let onDelete: (id: number) => void
    import { priorities } from "../main";

    function getPriority(value: number) {
        return priorities.find(p => p.value === value)?.name
    }

    

</script>

<div class="shadow-lg border rounded-lg border-gray-300 p-4 flex flex-col gap-2 relative">
  <h2 class="font-bold">{task.name}</h2>
  <p class="text-sm">{task.description || 'No description'}</p>
  <p class="text-sm font-semibold text-gray-600">{getPriority(task.priority)}</p>

  <button class="cursor-pointer text-sm absolute top-0 right-0 p-2 text-gray-600 font-semibold" on:click={() => {onDelete(task.id)}}>Delete</button>
  
</div>