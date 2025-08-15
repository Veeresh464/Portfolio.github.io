document.getElementById('contactForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Stop the page from reloading

    // Get form data
    const formData = {
        name: document.getElementById('name').value.trim(),
        email: document.getElementById('email').value.trim(),
        subject: document.getElementById('subject').value.trim(),
        message: document.getElementById('message').value.trim(),
    };

    const responseMessage = document.getElementById('formResponseMessage');
    responseMessage.textContent = 'Sending...';

    try {
        // Send data to your Express API
        const response = await fetch('http://localhost:5000/contact', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData),
        });

        if (response.ok) {
            responseMessage.textContent = 'Message sent successfully!';
            document.getElementById('contactForm').reset(); // Clear the form
        } else {
            const errorData = await response.json();
            responseMessage.textContent = `Error: ${errorData.error || 'Failed to send message.'}`;
        }
    } catch (error) {
        console.error('Submission error:', error);
        responseMessage.textContent = 'A network error occurred.';
    }
});
