console.log(1)
for await (const file of Deno.readDir('/')) {
    console.log(file);
}
console.log(2)
