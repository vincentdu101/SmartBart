import * as React from "react";
import * as ConfigService from "../../services/Config/ConfigService";
import "./PlannerTable.scss";

import { Table } from "reactstrap";

interface IState {
    plans: any;
}

  

class PlannerTable extends React.Component<void, IState> {

    constructor(props: any) {
        super(props);
        this.state = {plans: []};
    }

    public componentDidMount() {
        fetch(ConfigService.staticFilteredEstimates + "?orig=24th&dest=rock").then(results => {
            return results.json();
        }).then(data => {
            debugger;
            this.setState({plans: data});
        });
    }

    public render() {
        return (
            <Table>
                <thead>
                    <tr><td>Test</td></tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            {this.state.plans}
                        </td>
                    </tr>
                </tbody>
            </Table>
        );
    }

}

export default PlannerTable;