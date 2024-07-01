$(function() {


    const $loginForm = $('#loginForm');

    if($loginForm.length) {

        $loginForm.validate({

            rules: {
                email: {
                    required: true,
                    customEmail: true
                },
                password: {
                    required: true,
                }
            },
            messages: {
                email: {
                    required: 'Please enter the email',
                },
                password: {
                    required: 'Please enter the password',
                }
               
            }

        })
    }

})