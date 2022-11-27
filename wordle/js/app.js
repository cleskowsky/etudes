import game from './game.js';

document.addEventListener('alpine:init', () => {
    console.log('Alpine init called');

    Alpine.data('game', () => game);
});
