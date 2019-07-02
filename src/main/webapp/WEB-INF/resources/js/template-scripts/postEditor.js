var PostEditor = (function() {
    return {

        initialize: function (container) {
            PostEditor.initializeRichEditor(container.querySelector("#status"));
        },

        initializeRichEditor: function (editorElement) {
            // BalloonEditor
            //     .create( editorElement, {
            //         toolbar: [
            //             'heading',
            //             'bold',
            //             'italic',
            //             'link',
            //             'bulletedList',
            //             'numberedList',
            //             'blockQuote',
            //             'undo',
            //             'redo'
            //         ]
            //     } )
            //     .then( editor => {
            //         window.editor = editor;
            //     } )
            //     .catch( err => {
            //         console.error( err.stack );
            //     } );
        }
    }
}());
PostEditor.initialize(document.querySelector(".post-editor-content-wrapper"));