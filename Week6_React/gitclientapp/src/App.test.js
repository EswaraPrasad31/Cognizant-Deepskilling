import { render, screen } from '@testing-library/react';
import App from './App';

test('renders GitHub Repository Viewer title', () => {
  render(<App />);
  const titleElement = screen.getByText(/GitHub Repository Viewer/i);
  expect(titleElement).toBeInTheDocument();
});
