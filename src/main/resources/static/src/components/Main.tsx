import * as React from "react";
import { Switch, Route } from 'react-router-dom';
import PlannerSection from "./Sections/PlannerSection/PlannerSection";
import StationsSection from "./Sections/StationsSection/StationsSection";
import StationSection from "./Sections/StationSection/StationSection";

// The Main component renders one of the three provided
// Routes (provided that one matches). Both the /roster
// and /schedule routes will match any pathname that starts
// with /roster or /schedule. The / route will only match
// when the pathname is exactly the string "/"
export default class Main extends React.Component<any, any> {

    constructor(props: any) {
        super(props);
    }

    render() {
        return (
            <main>
                <Switch>
                    <Route exact={true} path="/" component={StationsSection} />
                    <Route path="/planner" component={PlannerSection} />
                    <Route path="/station/:abbr" component={StationSection} />
                </Switch>
            </main>
        );
    }

}
