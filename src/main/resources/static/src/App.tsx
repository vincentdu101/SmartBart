import * as React from 'react';
import './App.css';
import logo from './logo.svg';

// import PlannerSection from "./components/Sections/PlannerSection/PlannerSection";
import StationSection from "./components/Sections/StationSection/StationSection";

class App extends React.Component {
  public render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        
        {/* <PlannerSection /> */}
        <StationSection />
      </div>
    );
  }
}

export default App;
