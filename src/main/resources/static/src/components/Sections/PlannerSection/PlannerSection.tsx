import * as React from "react";
import "./PlannerSection.scss";
import { IPlannerSectionState, IPlannerSectionProps } from "../../../types/PlannerTypes";
import PlannerTable from "../../PlannerTable/PlannerTable";
import DropdownInfo from "../../Dropdown/DropdownInfo";
import * as ConfigService from "../../../services/Config/ConfigService";

export default class PlannerSection extends React.Component<{}, IPlannerSectionState> {

    constructor(props: IPlannerSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);
        this.destinationSelection = this.destinationSelection.bind(this);

        this.state = {
            stations: [],
            origin: "",
            destination: ""
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

    private originSelection(origin: string): void {
        console.log("origin ", origin);
        this.setState({origin: origin});
    }

    private destinationSelection(destination: string): void {
        console.log("destination ", destination);
        this.setState({destination: destination});
    }

    public componentDidMount(): void {
        this.loadStationsInfo();
    }

    public render(): JSX.Element {
        return (
            <section className="planner-section">
                <DropdownInfo   input={this.state.stations}
                                label="Origin" 
                                selectionCallback={this.originSelection} />
                <DropdownInfo   input={this.state.stations} 
                                label="Destination"
                                selectionCallback={this.destinationSelection} />
                <PlannerTable   origin={this.state.origin} 
                                destination={this.state.destination} />
            </section>
        );
    }

}