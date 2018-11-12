import * as React from 'react';
import './App.css';
import {IAppProps, IAppState} from "./types/AppTypes";
import Main from "./components/Main";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink} from 'reactstrap';

class App extends React.Component<IAppProps, IAppState> {

  constructor(props: IAppProps) {
      super(props);

      this.toggle.bind(this);

      this.state = {
          isOpen: false
      };
  }

  private toggle(): void {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  public render() {
    return (
      <div className="App">
        <div>
          <Navbar color="light" light={true} expand="md">
            <NavbarBrand href="/">SmartBart</NavbarBrand>
            <NavbarToggler onClick={this.toggle} />
            <Collapse isOpen={this.state.isOpen} navbar={true}>
              <Nav className="ml-auto" navbar={true}>
                <NavItem>
                  <NavLink href="/components/">Planner</NavLink>
                </NavItem>
                <NavItem>
                  <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
                </NavItem>
              </Nav>
            </Collapse>
          </Navbar>
        </div>
        
        <Main />
      </div>
    );
  }
}

export default App;
