document.addEventListener('alpine:init', () => {
    console.log('Alpine init called');

    Alpine.data('game', () => {
        return {
            guessesAllowed: 3,
            wordLength: 3,
            currentRowIndex: 0,
            currentTileIndex: 0,

            init() {
                console.log('Init called');
                this.board = Array.from({length: this.guessesAllowed}, () => {
                    return Array.from({length: this.wordLength}, () => 'X');
                });
            },

            onKeyPress(key) {
                if (/^[A-z]$/.test(key)) {
                    this.board[0][0] = key;
                }
            }
        }
    })
});
