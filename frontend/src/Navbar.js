import React, { Component } from 'react';
import { Menu, Container } from 'semantic-ui-react';
import { Link, withRouter } from 'react-router-dom';

class Navbar extends Component {
  render() {
    return (
      <Menu fixed='top'>
        <Container>
          <Menu.Item header>Flugleitarvél</Menu.Item>
          <Menu.Item as={Link} to='/'>Bóka flug</Menu.Item>
          <Menu.Item as={Link} to='/flight'>Flugupplýsingar</Menu.Item>
          <Menu.Item as={Link} to='/booking'>Bókunin mín</Menu.Item>
        </Container>
      </Menu>
    );
  }
}

export default withRouter(Navbar);
