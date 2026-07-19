import React, { useState } from 'react';

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
  });

  const [errors, setErrors] = useState({
    name: '',
    email: '',
    password: '',
  });

  const [touched, setTouched] = useState({
    name: false,
    email: false,
    password: false,
  });

  // Regular expression for email validation (must contain @ and .)
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  const validateField = (fieldName, value) => {
    let errorMsg = '';
    switch (fieldName) {
      case 'name':
        if (!value || value.trim().length < 5) {
          errorMsg = 'Name should contain at least 5 characters.';
        }
        break;
      case 'email':
        if (!value || !emailRegex.test(value.trim())) {
          errorMsg = 'Enter a valid email address.';
        }
        break;
      case 'password':
        if (!value || value.length < 8) {
          errorMsg = 'Password should contain at least 8 characters.';
        }
        break;
      default:
        break;
    }
    return errorMsg;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));

    // Real-time validation if touched or error exists
    if (touched[name] || errors[name]) {
      const errorMsg = validateField(name, value);
      setErrors((prev) => ({ ...prev, [name]: errorMsg }));
    }
  };

  const handleBlur = (e) => {
    const { name, value } = e.target;
    setTouched((prev) => ({ ...prev, [name]: true }));
    const errorMsg = validateField(name, value);
    setErrors((prev) => ({ ...prev, [name]: errorMsg }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Mark all fields as touched
    setTouched({
      name: true,
      email: true,
      password: true,
    });

    // Validate all fields
    const nameErr = validateField('name', formData.name);
    const emailErr = validateField('email', formData.email);
    const passErr = validateField('password', formData.password);

    setErrors({
      name: nameErr,
      email: emailErr,
      password: passErr,
    });

    // If any error exists, prevent submission
    if (nameErr || emailErr || passErr) {
      return;
    }

    // Submission success
    alert('Registration Successful!');

    // Reset form
    setFormData({ name: '', email: '', password: '' });
    setTouched({ name: false, email: false, password: false });
    setErrors({ name: '', email: '', password: '' });
  };

  return (
    <div className="mail-app">
      <h1 className="app-title">Mail Registration</h1>
      <div className="form-container">
        <form onSubmit={handleSubmit} noValidate>
          {/* Name Field */}
          <div className="form-group">
            <label className="form-label" htmlFor="name">
              Name
            </label>
            <input
              type="text"
              id="name"
              name="name"
              className={`form-control ${errors.name ? 'input-error' : ''}`}
              value={formData.name}
              onChange={handleChange}
              onBlur={handleBlur}
              placeholder="Enter your name"
            />
            {errors.name && <div className="error-message">{errors.name}</div>}
          </div>

          {/* Email Field */}
          <div className="form-group">
            <label className="form-label" htmlFor="email">
              Email
            </label>
            <input
              type="email"
              id="email"
              name="email"
              className={`form-control ${errors.email ? 'input-error' : ''}`}
              value={formData.email}
              onChange={handleChange}
              onBlur={handleBlur}
              placeholder="Enter your email"
            />
            {errors.email && <div className="error-message">{errors.email}</div>}
          </div>

          {/* Password Field */}
          <div className="form-group">
            <label className="form-label" htmlFor="password">
              Password
            </label>
            <input
              type="password"
              id="password"
              name="password"
              className={`form-control ${errors.password ? 'input-error' : ''}`}
              value={formData.password}
              onChange={handleChange}
              onBlur={handleBlur}
              placeholder="Enter password"
            />
            {errors.password && (
              <div className="error-message">{errors.password}</div>
            )}
          </div>

          {/* Register Button */}
          <div className="button-container">
            <button type="submit" className="register-btn">
              Register
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
