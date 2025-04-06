// Form Validation for Booking Page
document.addEventListener('DOMContentLoaded', function() {
    const bookingForm = document.getElementById('booking-form');
    
    if (bookingForm) {
        bookingForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Reset error messages
            document.querySelectorAll('.error-message').forEach(el => {
                el.textContent = '';
            });

            let isValid = true;

            // Validate Patient Name
            const nameInput = document.getElementById('patient-name');
            if (!nameInput.value.trim()) {
                document.getElementById('name-error').textContent = 'Please enter your name';
                isValid = false;
            }

            // Validate Contact Number
            const contactInput = document.getElementById('contact-number');
            const phoneRegex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
            if (!contactInput.value.trim()) {
                document.getElementById('contact-error').textContent = 'Please enter your contact number';
                isValid = false;
            } else if (!phoneRegex.test(contactInput.value)) {
                document.getElementById('contact-error').textContent = 'Please enter a valid phone number';
                isValid = false;
            }

            // Validate Doctor Selection
            const doctorSelect = document.getElementById('doctor-select');
            if (!doctorSelect.value) {
                document.getElementById('doctor-error').textContent = 'Please select a doctor';
                isValid = false;
            }

            // Validate Date
            const dateInput = document.getElementById('appointment-date');
            if (!dateInput.value) {
                document.getElementById('date-error').textContent = 'Please select a date';
                isValid = false;
            } else {
                const selectedDate = new Date(dateInput.value);
                const today = new Date();
                today.setHours(0, 0, 0, 0);
                
                if (selectedDate < today) {
                    document.getElementById('date-error').textContent = 'Please select a future date';
                    isValid = false;
                }
            }

            // Validate Time
            const timeInput = document.getElementById('appointment-time');
            if (!timeInput.value) {
                document.getElementById('time-error').textContent = 'Please select a time';
                isValid = false;
            }

            // If form is valid, redirect to confirmation page
            if (isValid) {
                // In a real app, you would send data to server here
                // For demo, we'll just redirect with sample data
                const queryParams = new URLSearchParams();
                queryParams.append('doctor', doctorSelect.value);
                queryParams.append('date', new Date(dateInput.value).toLocaleDateString());
                queryParams.append('time', timeInput.value);
                queryParams.append('patient', nameInput.value);
                
                window.location.href = `confirmation.html?${queryParams.toString()}`;
            }
        });
    }

    // Parse URL parameters on confirmation page
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.size > 0 && document.getElementById('confirmation-number')) {
        document.getElementById('doctor-name').textContent = urlParams.get('doctor') || 'Dr. Sarah Johnson';
        document.getElementById('appointment-date').textContent = urlParams.get('date') || 'November 15, 2023';
        document.getElementById('appointment-time').textContent = urlParams.get('time') || '10:30 AM';
        document.getElementById('patient-name').textContent = urlParams.get('patient') || 'John Doe';
        
        // Generate random confirmation number if not in URL
        if (!urlParams.get('confirmation')) {
            const randomNum = Math.floor(Math.random() * 90000) + 10000;
            document.getElementById('confirmation-number').textContent = `MC-2023-${randomNum}`;
        }
    }

    // Mobile menu toggle functionality
    const mobileMenuButton = document.querySelector('button.md\\:hidden');
    if (mobileMenuButton) {
        mobileMenuButton.addEventListener('click', function() {
            const navLinks = this.parentElement.querySelector('.hidden.md\\:flex');
            if (navLinks) {
                navLinks.classList.toggle('mobile-menu');
                navLinks.classList.toggle('active');
            }
        });
    }
});