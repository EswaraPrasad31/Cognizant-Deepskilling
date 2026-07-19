import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false,
    };
  }

  async loadPosts() {
    try {
      const response = await fetch('https://jsonplaceholder.typicode.com/posts');
      const data = await response.json();
      const posts = data.map(item => new Post(item.id, item.title, item.body));
      this.setState({ posts });
    } catch (error) {
      console.error(error);
      this.setState({ hasError: true });
    }
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    this.setState({ hasError: true });
    alert(error.message);
    console.error(error);
  }

  render() {
    const { posts, hasError } = this.state;

    if (hasError) {
      return <h2>Something went wrong.</h2>;
    }

    if (posts.length === 0) {
      return <h2>Loading Posts...</h2>;
    }

    return (
      <div>
        {posts.map(post => (
          <div key={post.id}>
            <h2>{post.title}</h2>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
