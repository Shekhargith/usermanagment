
import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  fullName: string = '';
  username: string = '';
  email: string = '';
  mobile: string = '';
  password: string = '';
  confirmPassword: string = '';

  acceptTerms: boolean = false;
  showPassword: boolean = false;
  isSubmitting: boolean = false;

  message: string = '';
  errorMessage: string = '';

  availableRoles: string[] = [
    'USER',
    'ADMIN',
    'MANAGER',
    'HR',
    'SUPERVISOR'
  ];

  selectedRoles: string[] = [];

  constructor(private userService: UserService) {}

  onRoleChange(role: string, event: Event) {

    const checked = (event.target as HTMLInputElement).checked;

    if (checked) {
      if (!this.selectedRoles.includes(role)) {
        this.selectedRoles.push(role);
      }
    } else {
      this.selectedRoles = this.selectedRoles.filter(
        item => item !== role
      );
    }
  }

  register() {

    this.message = '';
    this.errorMessage = '';

    if (!this.fullName || !this.username ||
        !this.email || !this.mobile ||
        !this.password || !this.confirmPassword) {

      alert('Please fill all required fields');
      return;
    }

    if (this.password !== this.confirmPassword) {
      alert('Password and Confirm Password do not match');
      return;
    }

    if (this.password.length < 8 || this.password.length > 72) {
      alert('Password must be between 8 and 72 characters');
      return;
    }

    if (!this.acceptTerms) {
      alert('Please accept Terms & Conditions');
      return;
    }

    if (this.selectedRoles.length === 0) {
      alert('Please select at least one user role');
      return;
    }

    // Backend request object
    const requestData = {
      username: this.username,
      email: this.email,
      password: this.password,
      fullName: this.fullName,
      mobileNo: this.mobile,
      roles: this.selectedRoles
    };

    this.isSubmitting = true;

    // Call Spring Boot API
    this.userService.saveUser(requestData).subscribe({

      next: (response) => {

        console.log('Registration Response:', response);

        this.message = 'User registered successfully!';

        alert('User registered successfully!');

        // Clear form after successful save
        this.fullName = '';
        this.username = '';
        this.email = '';
        this.mobile = '';
        this.password = '';
        this.confirmPassword = '';
        this.selectedRoles = [];
        this.acceptTerms = false;

        this.isSubmitting = false;
      },

      error: (error) => {

        console.error('Registration Error:', error);

        this.errorMessage =
          error.error?.message ||
          'Registration failed. Please try again.';

        alert(this.errorMessage);

        this.isSubmitting = false;
      }

    });
  }
}
