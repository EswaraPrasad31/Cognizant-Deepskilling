import React from 'react';
import { blogs, showBlogs } from '../data';

// Conditional Rendering: Element Variables (Method 4)
const BlogDetails = () => {
  // Method 4: Element Variable
  let content;
  if (showBlogs) {
    content = (
      <div>
        {blogs.map((blog) => (
          <div key={blog.id} style={{ marginBottom: '25px' }}>
            <p style={{ fontSize: '26px', fontWeight: 'bold', margin: '0' }}>
              {blog.title}
            </p>
            <p style={{ fontSize: '18px', fontWeight: 'bold', margin: '4px 0 2px 0', color: '#333' }}>
              {blog.author}
            </p>
            <p style={{ fontSize: '16px', margin: '0' }}>
              {blog.description}
            </p>
          </div>
        ))}
      </div>
    );
  } else {
    content = <p>No blogs available.</p>;
  }

  return (
    <div>
      <h2 className="panel-heading">Blog Details</h2>
      {content}
    </div>
  );
};

export default BlogDetails;
