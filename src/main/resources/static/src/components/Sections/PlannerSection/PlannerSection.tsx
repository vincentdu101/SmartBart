import * as React from "react";
import "./PlannerSection.scss";
import { IPlannerSectionState, IPlannerSectionProps } from "../../../types/PlannerTypes";
import PlannerTable from "../../PlannerTable/PlannerTable";
import DropdownInfo from "../../Dropdown/DropdownInfo";
import * as ConfigService from "../../../services/Config/ConfigService";

export default class PlannerSection extends React.Component<{}, IPlannerSectionState> {

    constructor(props: IPlannerSectionProps) {
        super(props);

        this.loadStationsInfo.bind(this);

        this.state = {
            stations: []
        };
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        fetch(ConfigService.staticStationsInfo).then(results => {
            return results.json();
        }).then(data => {
            this.setState({stations: data});
        });
    }

    public componentDidMount(): void {
        this.loadStationsInfo();
    }

    public render(): JSX.Element {
        return (
            <section className="planner-section">
                <DropdownInfo stations={this.state.stations} />
                <PlannerTable />
            </section>
        );
    }

}